#include <iostream>
#include <algorithm>
using namespace std;

int r, c;
int result = 987654321;

char board[51][51];
char WB[] = { 'W','B' };

void input() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> r >> c;

	for (int i = 0; i < r; i++) {
		for (int j = 0; j < c; j++) {
			cin >> board[i][j];
		}
	}
}

int getMin(int y, int x, int flag) {
	int count = 0;
	for (int i = y; i < y + 8; i++) {
		for (int j = x; j < x + 8; j++) {
			if (board[i][j] != WB[flag])
				count++;
			flag = (flag + 1) % 2;
		}
		flag = (flag + 1) % 2;
	}
	return count;
}

void solution() {
	int maxR = r - 8;
	int maxC = c - 8;

	for (int i = 0; i <= maxR; i++) {
		for (int j = 0; j <= maxC; j++) {
			int temp = min(getMin(i, j, 0), getMin(i, j, 1));
			result = min(result, temp);
		}
	}
}

int main() {	
	input();
	solution();
	cout << result << endl;
	
	return 0;
}