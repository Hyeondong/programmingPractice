#include <iostream>
#include <string>
#include <vector>

#define MAX 51

typedef struct {
	int r, c, s;
} pos;

using namespace std;
vector<pos> v;
bool visit[3] = { false };
int n, m, k;
int map[MAX][MAX];
pos rotation_list[7];
int dy[] = {1, 0, -1, 0};
int dx[] = {0, 1, 0, -1};
int result = 987654321;


void input() {
	int r, c, s;
	
	cin >> n >> m >> k;

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			cin >> map[i][j];
		}
	}
	for (int i = 0; i < k; i++) {
		cin >> r >> c >> s;
		pos item;
		item.r = r-1;
		item.c = c-1;
		item.s = s;
		rotation_list[i] = item;
	}

}

int getMin(int arr[][MAX]) {
	int min = 987654321;

	for (int i = 0; i < n; i++) {
		int sum = 0;
		for (int j = 0; j < m; j++) {
			sum += arr[i][j];
		}
		if (sum < min) {
			min = sum;
		}
	}
	return min;
}

void rotate() {

	int arr[MAX][MAX];
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			arr[i][j] = map[i][j];
		}
	}

	for (int i = 0; i < v.size(); i++) {

		pos item = v[i];
		int sR = item.r - item.s;
		int sC = item.c - item.s;
		int S = item.s;
		
		while (S > 0) {
			int tempN = arr[sR][sC];
			int tempR = sR;
			int tempC = sC;
			

			for (int d = 0; d < 4; d++) {
				int dist = 2 * S + 1;

				while (dist > 1) {
					arr[tempR][tempC] = arr[tempR + dy[d]][tempC + dx[d]];
					tempR += dy[d];
					tempC += dx[d];
					dist--;
				}
			}
			arr[sR][sC + 1] = tempN;

			sR += 1;
			sC += 1;
			
			S--;
		}
	}

	int temp_min = getMin(arr);
	if (result > temp_min) {
		result = temp_min;
	}
}


void dfs(int cnt){
	if (cnt >= k) {
		
		rotate();

		return;
	}
	
	for (int i = 0; i < k; i++) {
		if (!visit[i]) {
			visit[i] = true;
			v.push_back(rotation_list[i]);
			
			dfs(cnt+1);
			
			visit[i] = false;
			v.pop_back();
		}
	}

}

int main() {
	input();
	dfs(0);
	cout << result << endl;
	return 0;
}
