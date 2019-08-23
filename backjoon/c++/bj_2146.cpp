#include <iostream>
#include <queue>
#include <algorithm>
using namespace std;

int dy[] = { -1, 0, 1, 0 };
int dx[] = { 0, 1, 0, -1 };

int N;
int arr[100][100];
bool visit[100][100];

void visit_clear() {
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			visit[i][j] = false;
		}
	}
}

void input() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);


	cin >> N;

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			cin >> arr[i][j];
		}
	}
}

void dfs(int n, int i, int j) {
	if (i<0 || j<0 || i>=N || j >= N || visit[i][j]) {
		return;
	}
	if (arr[i][j] == 0)
		return;

	visit[i][j] = true;
	arr[i][j] = n + 1;
	
	for (int d = 0; d < 4; d++) {
		dfs(n, i + dy[d], j + dx[d]);
	}
}

void Find_Cluster() {
	int num = 0;

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			if (arr[i][j]!=0 && !visit[i][j]) {
				dfs(num++, i, j);
			}
		}
	}
}

int bfs(int i, int j) {
	visit_clear();

	queue<pair<int, int>> q;
	q.push(make_pair(i, j));
	visit[i][j] = true;

	int cnt = -1;


	while (!q.empty()) {
		int q_size = q.size();
		cnt++;
		while (q_size--) {
			
			int temp_y = q.front().first;
			int temp_x = q.front().second;
			q.pop();
			
			for (int d = 0; d < 4; d++) {
				int next_y = temp_y + dy[d];
				int next_x = temp_x + dx[d];

				if (next_y < 0 || next_x < 0 || next_y >= N || next_x >= N || visit[next_y][next_x]) 
					continue;
				
				if (arr[next_y][next_x] != arr[i][j] && arr[next_y][next_x] != 0)
					return cnt;

				visit[next_y][next_x] = true;
				q.push(make_pair(next_y, next_x));
			}
		}
	}

	return 987654321;
}

int find_distance() {
	int result = 987654321;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			if (arr[i][j] != 0) {
				result = min(result, bfs(i,j));
			}
		}
	}
	return result;
}

int main() {
	input();
	Find_Cluster();

	cout << find_distance() << endl;
}