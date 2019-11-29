import random

def build_adj_lst(graph):
    """Makes a copy of graph, and returns an adjacency list
    in the form of a python dictionary"""
    cgraph = graph[:]
    adj_lst = {}
    for item in cgraph:
        x, y = item[0], item[1]
        if x not in adj_lst.keys():
            adj_lst.update({x:[]})
        else:
            adj_lst.update({y:[]})
    while cgraph:
        edge = cgraph.pop()
        adj_lst[edge[0]].append(edge[1])
        adj_lst[edge[1]].append(edge[0])
    return adj_lst

def check_tour_possibility(adj_lst):
    """Checks if any of the vertices have odd degree via checking length
     of adjacent nodes list. If any are odd--a circuit/tour is not possible"""
    for key in adj_lst.keys():
        if len(adj_lst[key]) % 2 != 0:
            return False
    return True

def choose_random_node(graph):
    """Checks to see if there are any disconnected nodes, and returns a random
    node from the possible nodes in graph"""
    choice_list = []
    for key in graph.keys():
        if graph[key]:
            choice_list.append(key)
    return random.choice(choice_list)

def build_subtour(graph, start):
    """Takes a starting node, and creates a sub-tour of the graph, returning
    the subtour as a list of nodes"""
    start_node = start
    subtour = [start_node]
    current_node = start_node
    next_node = graph[current_node].pop()
    while next_node != start_node:
        subtour.append(next_node)
        graph[next_node].remove(current_node)
        current_node = next_node
        next_node = graph[current_node].pop()
    graph[next_node].remove(current_node)
    subtour.append(next_node)
    return subtour

def check_graph_if_values_empty(graph):
    """Helper function to check if all edges have been taken"""
    values_list = []
    for value in graph.values():
        for num in value:
            values_list.append(num)
    if values_list:
        return True
    else:
        return False

def build_tour(graph):
    """Builds the overall tour from subtours, returning the tour"""
    tour = []
    while check_graph_if_values_empty(graph):
        if not tour:
            start = choose_random_node(graph)
            tour = build_subtour(graph, start)
        else:
            start_list = [node for node in tour if graph[node]]
            start = start_list[-1]
            subtour = build_subtour(graph, start)
            sub_counter = len(subtour) - 1
            index = tour.index(subtour[0])
            tour.remove(subtour[0])
            while sub_counter >= 0:
                tour.insert(index, subtour[sub_counter])
                sub_counter -= 1
    return tour

def find_eulerian_tour(graph):
    """Builds adjacency list from edge list, checks if tour is possible, and
    returns tour"""
    adj_lst = build_adj_lst(graph)
    if not check_tour_possibility(adj_lst):
        return False
    else:
        return build_tour(adj_lst)

# first time through--pick any start node;
# subsequent times through--pick a value in tour that has values.
#
# build subtour from start node ending on start node,
# if first time through--just append this to tour.
# otherwise, find subtour[0] in tour, insert each item in subtour at this position

graph1 = [(1, 2), (2, 3), (3, 1)]
graph2 = [(0, 1), (1, 5), (1, 7), (4, 5),(4, 8), (1, 6), (3, 7), (5, 9), (2, 4),
            (0, 4), (2, 5), (3, 6), (8, 9)]
graph3 = [(1, 13), (1, 6), (6, 11), (3, 13), (8, 13), (0, 6), (8, 9), (5, 9),
            (2, 6), (6, 10), (7, 9), (1, 12), (4, 12), (5, 14), (0, 1), (2, 3),
            (4, 11), (6, 9), (7, 14), (10, 13)]
graph4 = [(8, 16), (8, 18), (16, 17), (18, 19), (3, 17), (13, 17), (5, 13),
            (3, 4), (0, 18), (3, 14), (11, 14), (1, 8), (1, 9), (4, 12),
            (2, 19),(1, 10), (7, 9), (13, 15), (6, 12), (0, 1), (2, 11),
            (3, 18), (5, 6), (7, 15), (8, 13), (10, 17)]

print(find_eulerian_tour(graph1))
print(find_eulerian_tour(graph2))
print(find_eulerian_tour(graph3))
print(find_eulerian_tour(graph4))
