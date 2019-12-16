import heapq
import sys
inf = float("inf")
# GALACTIC-TAXES
# Juan Sebastian Gonzalez Rivera
# Politécnico Grancolombiano - 2019

# Clase nodo


class Node:
    def __init__(self, id, value):
        self.value = value
        self.id = id

    def __lt__(self, other):
        return self.value < other.value


def dijkstra(graph, n, t):
    edges = [inf for i in range(n)]
    edges[0] = 0
    queue = []
    heapq.heappush(queue, Node(0, 0))
    # Por cada uno de los nodos
    while len(queue) > 0:
        actualNode = heapq.heappop(queue)
        # Recorre cada uno de los vecinos (nexNode)
        for nextNode in graph[actualNode.id]:
            cost = nextNode["A"] * t + nextNode["B"]
            if(edges[nextNode["id"]] > edges[actualNode.id] + cost):
                edges[nextNode["id"]] = edges[actualNode.id] + cost
                newNode = Node(nextNode["id"], edges[nextNode["id"]])
                heapq.heappush(queue, newNode)
    return edges[n-1]


def trisection(graph, n, low, high):
    left = 0,
    right = 0
    # 100 iteraciones de presición
    for i in range(100):
        thirdPart = (high-low)/3
        left = low + thirdPart
        right = high - thirdPart
        if(dijkstra(graph, n, left) < dijkstra(graph, n, right)):
            low = left
        else:
            high = right

    t = (low + high) / 2
    taxes = dijkstra(graph, n, t)
    return "{0:.5f}".format(round(taxes, 5))


out = []
#  sys.stdin
for nm in sys.stdin:

    if(nm == "\n"):
        break
    nm = nm.split()
    n = int(nm[0])  # Vertices (ACM Officces)
    m = int(nm[1])  # Artistas (Connections)

    low = 0  # minuto 0
    high = 24*60  # (minuto 1440)

    graph = [[] for x in list(range(n))]

    for i in range(m):
        params = sys.stdin.readline()
        params = list(map(lambda x: int(x), params.split()))
        I = params[0]
        J = params[1]
        A = params[2]
        B = params[3]
        graph[I-1].append({"A": A, "B": B, "id": J-1})
        graph[J-1].append({"A": A, "B": B, "id": I-1})
    print(trisection(graph, n, low, high))

