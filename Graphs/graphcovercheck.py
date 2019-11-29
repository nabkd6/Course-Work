# Write two functions, the first, validity_check which takes a potential
# cover and the adjacency matrix of a graph as its inputs and returns True
# if the potential cover is a cover of the graph and False otherwise.
# The second, vertex_cover_naive, takes the adjacency matrix of a graph
# as its input, checks all potential covers, and returns the size of a
# minimum vertex cover. You should assume there are no loops in the graph.

# def vertex_cover_naive(input_graph):
#     n = len(input_graph)
#     minimum_vertex_cover = n
#     # loops through all strings of 0s and 1s of length n
#     for assignment in product([0,1], repeat=n):
#         # Your code should go here.
#         # Based on the assignment (a list of 0s and 1s)
#         # - Check the assignment is valid
#         # - Calculate the size of assignment
#         # - Update the minimum_vertex_cover variable if appropriate
#
#
#     # End of your code
#     return minimum_vertex_cover
#
# def test():
#     graph = [[0, 1, 1, 1, 1],
#         [1, 0, 0, 0, 1],
#         [1, 0, 0, 1, 1],
#         [1, 0, 1, 0, 1],
#         [1, 1, 1, 1, 0]]
#
#     assert vertex_cover_naive(graph)==3

# If you've not seen testing like this before, all you need to do is
# to call test(). If the tests pass, you'll get no output. If they don't
# you'll get an assertion error. Don't forget to remove the call to the
# test before submitting your code.

from itertools import *
import copy


def validity_check(cover, graph):
    #copy graph
    graph2 = copy.deepcopy(graph)
    #set counter to use for indices
    k = 0
    #loop over items in cover--we need to check if they are 0 or 1
    while k < len(cover):
        # if we have a 1 in the cover--that means that vertex is "on"
        if cover[k] == 1:
            # for each item in the graph, we need to turn all 1s to 0s for the respective vertex in the cover
            for i in range(0, len(graph2)):
                # change it in both spots-- the "on" vertex column and the "on" vertex row
                graph2[i][k] = 0
                graph2[k][i] = 0
            #increment
            k += 1
        else:
            #increment
            k += 1
    #actual check process. If all the items in the graph are now set to 0-->
    #the cover works as all edges are removed successfully by the cover
    for item in graph2:
        for val in item:
            if val == 1:
                return False
    return True

def vertex_cover_naive(input_graph):
    n = len(input_graph)
    minimum_vertex_cover = n
    # loops through all strings of 0s and 1s of length n
    for assignment in product([0,1], repeat=n):
        # - Check the assignment is valid
        if validity_check(assignment, input_graph):
            # - Calculate the size of assignment
            size = 0
            for item in assignment:
                if item == 1:
                    size += 1
            # - Update the minimum_vertex_cover variable if appropriate
            if size < minimum_vertex_cover:
                minimum_vertex_cover = size
    return minimum_vertex_cover

graph =[[0, 1, 1, 1, 1],
        [1, 0, 0, 0, 1],
        [1, 0, 0, 1, 1],
        [1, 0, 1, 0, 1],
        [1, 1, 1, 1, 0]]

cover = [0, 1, 1, 1, 1]

print(validity_check(cover, graph))
# validity_check(cover, graph)
# since cover[1] --> anything in graph[1][x] becomes 0 and anything in graph[x][1] becomes 0
