#include <iostream>
using namespace std;

int n;
int result = 0;
int map[17][17];
int dy[] = { 0,1,1 };
int dx[] = { 1,1,0 };

void input() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> n;

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			cin >> map[i][j];
		}
	}
}

void dfs(int status, int r, int c) {

	if (r<0 || c<0 || r>n - 1 || c>n - 1 || map[r][c] == 1)
		return;
	if (status == 1 && (map[r - 1][c] == 1 || map[r][c - 1] == 1))
		return;

	if (r == n - 1 && c == n - 1) {
		result++;
		return;
	}

	for (int i = -1; i <= 1; i++) {
		int next_status = status + i;

		int next_r = r + dy[next_status];
		int next_c = c + dx[next_status];

		if (next_status >= 0 && next_status <= 2) {
			dfs(next_status, next_r, next_c);
		}
	}
}

int main() {
	input();
	dfs(0, 0, 1);
	cout << result << endl;
	return 0;
}