package com.mei.test.datastruct.graph;

public class Dijkstra {
	Graph graph;
	int[] verticeS;  //集合S表示已经查找到最短路径的节点的集合
	int sourceP;     // 源点
	int[] distance;  // 源点到其他点的距离,其中下标表示到达的点
	int[][] edge;    // 图的边的矩阵
	
	public Dijkstra(Graph g, int sourcePoint) {
		graph = g;
		sourceP= sourcePoint;
		verticeS = new int[graph.getVerticesSize()];
		distance = new int[graph.getVerticesSize()];
		edge = graph.getMatrix();
		calculate();
		display();
	}

	private void display() {
		for(int i = 0; i < graph.getVerticesSize(); i++) {
			System.out.println(i + ": " + distance[i]);
		}
		
	}

	private void calculate() {

		int minDis;
		int u = 0;
		// 初始化
		for(int i = 0; i < graph.getVerticesSize(); i++) {
			distance[i] = edge[sourceP][i];
			verticeS[i] = 0;
		}
		verticeS[sourceP] = 1;

		//轮询
		for(int i = 1; i < graph.getVerticesSize(); i++) {
			minDis = graph.MAX_WEIGHT;
			// 1.查找最短的那条边
			// 将找到的边对应的节点保存到u
			for(int j = 0; j < graph.getVerticesSize(); j++) {
				if (verticeS[j] == 0 && distance[j] < minDis) {
					u = j;
					minDis = distance[j];
				}
			}

			//说明已经找完了
			if (minDis == graph.MAX_WEIGHT) {
				return;
			}
			verticeS[u] = 1;
			//2. 更新distance,也就是起始点到其他未访问节点的距离
			for(int j = 0; j < graph.getVerticesSize(); j++) {
				if (verticeS[j] == 0 && edge[u][j] < Graph.MAX_WEIGHT) {
					if (distance[u] + edge[u][j] < distance[j]) {
						distance[j] = distance[u] + edge[u][j];
					}
				}
			}

		}



	}
}
