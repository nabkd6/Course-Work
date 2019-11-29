# Find Eulerian Tour
#Choose any starting vertex v, and follow a trail of edges from that vertex until returning to v.
# --It is not possible to get stuck at any vertex other than v, because the even
# degree of all vertices ensures that, when the trail enters another vertex w
# there must be an unused edge leaving w. The tour formed in this way is a
# closed tour, but may not cover all the vertices and edges of the initial graph.
# --As long as there exists a vertex u that belongs to the current tour but that
# has adjacent edges not part of the tour, start another trail from u, following
# unused edges until returning to u, and join the tour formed in this way to the
# previous tour.
# Write a function that takes in a graph
# represented as a list of tuples
# and return a list of nodes that
# you would follow on an Eulerian Tour
#
# For example, if the input graph was
# [(1, 2), (2, 3), (3, 1)] --> these are edges.
# A possible Eulerian tour would be [1, 2, 3, 1] --> these are nodes

#base case: graph[lengraph][0] == graph[index of tuple][index within tuple-1]; and all edges gone from graph.
    #to_visit = graph[:]
    #current_edge = to_visit.pop()
    #
import random

def create_tour(graph):
    """Takes a graph in format of an edge list,
    returns one possible Eulerian Tour in format of list of nodes """
    # your code here
    to_visit = graph[:]
    x = to_visit.pop(random.randrange(len(to_visit)))
    random_node_start = x[0]
    next_node = x[1]
    visited = [random_node_start, next_node]
    while len(to_visit) > 0:
        for item in to_visit:
            for element in item:
                if next_node == element:
                    visited.append(item[1])
                    next_node = item[1]
                    to_visit.remove(item)
                else:
                    pass
    return visited

def get_degree(tour):
    degree = {}
    for x, y in tour:
        degree[x] = degree.get(x, 0) + 1
        degree[y] = degree.get(y, 0) + 1
    return degree

def check_edge(t, b, nodes):
    """
    t: tuple representing an edge
    b: origin node
    nodes: set of nodes already visited

    if we can get to a new node from `b` following `t`
    then return that node, else return None
    """
    if t[0] == b:
        if t[1] not in nodes:
            return t[1]
    elif t[1] == b:
        if t[0] not in nodes:
            return t[0]
    return None

def connected_nodes(tour):
    """return the set of nodes reachable from
    the first node in `tour`"""
    a = tour[0][0]
    nodes = set([a])
    explore = set([a])
    while len(explore) > 0:
        # see what other nodes we can reach
        b = explore.pop()
        for t in tour:
            node = check_edge(t, b, nodes)
            if node is None:
                continue
            nodes.add(node)
            explore.add(node)
    return nodes

def is_eulerian_tour(nodes, tour):
    # all nodes must be even degree
    # and every node must be in graph
    degree = get_degree(tour)
    for node in nodes:
        try:
            d = degree[node]
            if d % 2 == 1:
                print("Node %s has odd degree" % node)
                return False
        except KeyError:
            print("Node %s was not in your tour" % node)
            return False
    connected = connected_nodes(tour)
    if len(connected) == len(nodes):
        return True
    else:
        print("Your graph wasn't connected")
        return False

def test():
    nodes = [20, 21, 22, 23, 24, 25]
    tour = create_tour(nodes)
    return is_eulerian_tour(nodes, tour)


graph = [(1, 2), (2, 3), (3, 1)]
#>>>output should be [1, 2, 3, 1]
graph2 = [(0, 1), (1, 5), (1, 7), (4, 5),
(4, 8), (1, 6), (3, 7), (5, 9),
(2, 4), (0, 4), (2, 5), (3, 6), (8, 9)]
print(create_tour(graph2))
