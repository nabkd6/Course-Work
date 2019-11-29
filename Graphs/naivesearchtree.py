

def vertex_cover_tree(input_graph):
    n = len(input_graph)
    assignment = [None]*n
    return recursive_vertex_cover(input_graph, assignment)

def recursive_vertex_cover(input_graph, assignment):
    n = len(input_graph)
    v = -1
    # loop through assignments--if it is set to None, let's check it--so v = i
    for i in range(n):
        if assignment[i] == None:
            v = i
        #checks if assignments have happened which would make a vcover imossible
        for j in range(i, n):
            if input_graph[i][j] == 1:
                #if both assignments of the vertex in question are 0, we missed an edge.
                if assignment[i] == 0 and assignment[j] == 0:
                    return float("inf")
    #check how many assignments have happened, and return the size of those assignments
    if v == -1:
        size = 0
        for i in range(n):
            if assignment[i] == 1:
                size += 1
            elif assignment[i] == None:
                # have to set vertex i to 1 if a neighbor is 0
                for j in range(n):
                    if input_graph[i][j] == 1:
                        if assignment[j] == 0:
                            size += 1
                            break
        return size
