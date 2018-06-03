package com.mei.test.datastruct.graph;


public class Kruskal {
	private int verticeSize;// 顶点数量
	private int[] vertices; // 顶点数组
	private int[][] matrix; // 矩阵
	private int edgeSize;
	private static final int MAX_WEIGHT = 0xFFF8;
	private Edge[] edges;

	public Kruskal(int verticeSize) {
		this.verticeSize = verticeSize;
		matrix = new int[verticeSize][verticeSize];
	}

	/**
	 * 获取途中所有的边
	 * @return
	 */
	private Edge[] getEdges() {
		int index = 0;
		Edge[] edges = new Edge[verticeSize * verticeSize];
		for(int i = 0;i < verticeSize; i++) {
			for(int j = 0; j < verticeSize;j++) {
				if (matrix[i][j] != 0 && matrix[i][j] != MAX_WEIGHT) {
					edges[index++] = new Edge(i, j, matrix[i][j]);
				}
			}
		}
		edgeSize = index;
		return edges;
	}

	public void kruskal() {
		edges = getEdges();
		int index = 0;
		Edge[] cur_edge = edges;
		Edge[] rets = new Edge[edgeSize];
		//edge_temp,下标表示边的起始点， 值表示边的终点
		//edge_temp[1] = 5;表示节点1 到节点5 有边
		int[] edge_temp = new int[edgeSize];
		sortEdge(cur_edge, edgeSize);
		for(int i = 0; i < edgeSize; i++) {
			int p1 = cur_edge[i].start;
			int p2 = cur_edge[i].end;
			//查找p1 和P2 是否联通
			int m = getEnd(edge_temp, p1);
			int n = getEnd(edge_temp, p2);
			if (m != n) {
				rets[index++] = cur_edge[i];
				if (m > n) {
					int temp = n;
					n = m;
					m = temp;
				}
				edge_temp[m] = n;
			}
		}

		int lengh = 0;
		for(int i = 0; i < index; i++) {
			lengh+= rets[i].weight;
		}
		System.out.println("最小生成树的权值：" + lengh);
		char[] chars = new char[verticeSize];
		chars[0] = 'A';
		chars[1] = 'B';
		chars[2] = 'C';
		chars[3] = 'D';
		chars[4] = 'E';
		chars[5] = 'F';
		chars[6] = 'G';

		for (int i = 0; i < index; i++) {
			System.out.printf("(%s, %s)---> %d \n",chars[rets[i].start], chars[rets[i].end], matrix[rets[i].start][rets[i].end]);
		}

	}

	/**
	 * 查找节点p 在连通图中的最后一个节点
	 * @param edge_temp
	 * @param p2
	 * @return
	 */

	private int getEnd(int[] edge_temp, int p) {
		// TODO Auto-generated method stub
		int i = p;
		while(edge_temp[i] != 0) {
			i = edge_temp[i];
		}
		return i;
	}

	private void sortEdge(Edge[] cur_edge, int size) {
		for(int i = 0; i < size; i++) {
			for (int j = i+1; j < size; j++) {
				if (edges[i].weight > edges[j].weight) {
					Edge tmp = edges[i];
					edges[i] = edges[j];
					edges[j] = tmp;
				}
			}
		}
	}

	public static class Edge{
		int start;
		int end;
		int weight;

		public Edge(int start, int end, int weight) {
			this.start = start;
			this.end = end;
			this.weight = weight;
		}

		public int getStart() {
			return start;
		}

		public void setStart(int start) {
			this.start = start;
		}

		public int getEnd() {
			return end;
		}

		public void setEnd(int end) {
			this.end = end;
		}

		public int getWeight() {
			return weight;
		}

		public void setWeight(int weight) {
			this.weight = weight;
		}

	}

	public static void main(String[] args) {
		Kruskal graph = new Kruskal(7);
		int[] v0 = new int[] {0, 50, 60, MAX_WEIGHT, MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT};
		int[] v1 = new int[] {50, 0, MAX_WEIGHT, 65, 40,MAX_WEIGHT,MAX_WEIGHT};
		int[] v2 = new int[] {60, MAX_WEIGHT, 0, 52, MAX_WEIGHT,MAX_WEIGHT,45};
		int[] v3 = new int[] {MAX_WEIGHT, 65, 52, 0, 50,30,42};
		int[] v4 = new int[] {MAX_WEIGHT, 40, MAX_WEIGHT, 50, 0,70,MAX_WEIGHT};
		int[] v5 = new int[] {MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, 30,70,0,MAX_WEIGHT};
		int[] v6 = new int[] {MAX_WEIGHT, MAX_WEIGHT, 45, 42,MAX_WEIGHT,MAX_WEIGHT,0};

		graph.matrix[0] = v0;
		graph.matrix[1] = v1;
		graph.matrix[2] = v2;
		graph.matrix[3] = v3;
		graph.matrix[4] = v4;
		graph.matrix[5] = v5;
		graph.matrix[6] = v6;

		graph.kruskal();
	}
}
