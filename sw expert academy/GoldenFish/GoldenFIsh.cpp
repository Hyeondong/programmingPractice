#include <iostream>
#include <fstream>
#include <string>
using namespace std;



int main() {
	int testcase;
	
	string isPossible="";
	ifstream input;
	input.open("input.txt");

	input >> testcase;
	cout << testcase << endl;
	
	for (int i = 0; i < testcase; i++) {
		int arrive_person[11112] = { 0, };
		int cur_sec = 0;
		int bread_num = 0;
		bool isTrue = true;
		int N, M, K;
		input >> N;
		input >> M;
		input >> K;
		for (int j = 0; j < N; j++) {
			int arrive_time;
			input >> arrive_time;
			arrive_person[arrive_time]++;
		}
		
		while (true) {
			
			if (cur_sec+M > 11111) {
				M = 11111-cur_sec;
			}
			for (int j = cur_sec+1; j < cur_sec+M; j++) {
				bread_num -= arrive_person[j];
				if (bread_num < 0) {
					isTrue = false;
					break;
				}
				arrive_person[j] = 0;
			}
			
			if (!isTrue) 
				break;
			
			if (cur_sec == 11111) {
				isTrue = true;
				break;
			}
			cur_sec += M;
			bread_num += K;
		}
		string temp = "#";
		temp.append(to_string(i+1));
		temp.append(" ");

		if (isTrue) {
			temp.append("Possible\n");
		}
		else {
			temp.append("Impossible\n");
		}
		isPossible.append(temp);
	}
	cout << isPossible << endl;
	input.close();
}
