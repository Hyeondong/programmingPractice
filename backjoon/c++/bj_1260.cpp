#include <iostream>
#include <vector>
#include <queue>
#include <functional>

using namespace std;

bool arr_graph[1001][1001];
priority_queue<int, vector<int>, greater<int> > list_graph[1001];
priority_queue<int, vector<int>, greater<int> > list_graph2[1001];

bool visit[1001];
int N, M, V;


void input_array() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> N >> M >> V;
	int from, to;

	for (int i = 0; i < M; i++) {
		cin >> from >> to;
		arr_graph[from][to] = true;
		arr_graph[to][from] = true;
	}
}

void input_list() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> N >> M >> V;
	int from, to;

	for (int i = 0; i < M; i++) {
		cin >> from >> to;
		list_graph[from].push(to);
		list_graph[to].push(from);
		list_graph2[from].push(to);
		list_graph2[to].push(from);
	}
}

void visit_clear() {
	for (int j = 0; j <= N; j++) {
		visit[j] = false;
	}
}

void solution_by_array_dfs(int node) {
	
	visit[node] = true;
	cout << node << " ";

	for (int j = 1; j <= N; j++) {
		if (!visit[j] && arr_graph[node][j]) {
			
			solution_by_array_dfs(j);
		}
	}
}

void solution_by_array_bfs(int start) {

	queue<int> q;

	visit[start] = true;
	q.push(start);

	while (!q.empty()) {

		int node = q.front();
		q.pop();
		cout << node << " ";
		for (int j = 1; j <= N; j++) {
			if (!visit[j] && arr_graph[node][j]) {
				visit[j] = true;
				q.push(j);
			}
		}
	}
	
	cout << endl;
}


void solution_by_list_dfs(int node) {
	visit[node] = true;
	cout << node << " ";
	
	while (!list_graph[node].empty()) {
		int next = list_graph[node].top();
		list_graph[node].pop();

		if (!visit[next]) {
			solution_by_list_dfs(next);
		}
	}
}

void solution_by_list_bfs(int start) {

	queue<int> q;

	q.push(start);
	visit[start] = true;

	while (!q.empty()) {
		int next = q.front();
		q.pop();
		cout << next << " ";

		while (!list_graph2[next].empty()) {
			int v = list_graph2[next].top();
			list_graph2[next].pop();
			
			if (!visit[v]) {
				q.push(v);
				visit[v] = true;
			}
		}
	}
	cout << endl;


}

int main() {
	/*
		인접 리스트 구현할 자료구조를 priority_queue를 사용안하고 한번에 소팅해두면
		dfs용 bfs용 따로 안만들어도됨

		소팅하는 시간비용이 손해일까?
		그래프 두개 만드는 공간비용이 더 손해일까?
		소팅하는 시간비용이 손해일듯
	*/

	//인접 행렬 그래프
/*
	input_array();
	
	solution_by_array_dfs(v);
	visit_clear();
	cout << endl;
	solution_by_array_bfs(v);
*/

	//인접 리스트 그래프

	input_list();

	solution_by_list_dfs(V);
	cout << endl;
	visit_clear();
	solution_by_list_bfs(V);

	return 0;
}