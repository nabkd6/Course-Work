
import copy

def inverse_graph(graph):
    """Returns the inverse of a graph"""
    graph2 = copy.deepcopy(graph)
    i = 0
    while i < len(graph2):
        j = 0
        while j < len(graph2):
            if i != j:
                if graph2[i][j] == 0:
                    graph2[i][j] = 1
                    j += 1
                elif graph2[i][j] == 1:
                    graph2[i][j] = 0
                    j += 1
            else:
                j += 1
        i += 1
    return graph2


def test():
    g1 = [[0, 1, 1, 0],
          [1, 0, 0, 1],
          [1, 0, 0, 1],
          [0, 1, 1, 0]]
    assert inverse_graph(g1) == [[0, 0, 0, 1],
                                 [0, 0, 1, 0],
                                 [0, 1, 0, 0],
                                 [1, 0, 0, 0]]
    g2 = [[0, 1, 1, 1],
          [1, 0, 1, 1],
          [1, 1, 0, 1],
          [1, 1, 1, 0]]
    assert inverse_graph(g2) == [[0, 0, 0, 0],
                                 [0, 0, 0, 0],
                                 [0, 0, 0, 0],
                                 [0, 0, 0, 0]]
g1 = [[0, 1, 1, 0],
      [1, 0, 0, 1],
      [1, 0, 0, 1],
      [0, 1, 1, 0]]

g2 = [[0, 1, 1, 1],
      [1, 0, 1, 1],
      [1, 1, 0, 1],
      [1, 1, 1, 0]]
print(g1)
print(inverse_graph(g1))
print(g1)

print(g2)
print(inverse_graph(g2))
print(g2)
test()
