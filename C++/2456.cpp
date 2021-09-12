#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <cstring>

using namespace std;

// https://www.acmicpc.net/problem/2456

int N;
int vote[4][4];
int result[4];

int main(void)
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> N;

	for (int i = 1; i <= 3; i++)
	{
		for (int j = 1; j <= 3; j++)
		{
			vote[i][j] = 0;
		}

		result[i] = 0;
	}

	for (int i = 0; i < N; i++)
	{
		for (int j = 1; j <= 3; j++)
		{
			int num;
			cin >> num;

			vote[j][num] += 1;

			result[j] += num * num;
		}
	}

	int sum1, sum2, sum3;
	sum1 = 3 * vote[1][3] + 2 * vote[1][2] + vote[1][1];
	sum2 = 3 * vote[2][3] + 2 * vote[2][2] + vote[2][1];
	sum3 = 3 * vote[3][3] + 2 * vote[3][2] + vote[3][1];

	if (result[1] > result[2]) {
		if (result[1] > result[3]) {
			cout << 1 << " " << sum1 << endl;
		}

		else if (result[1] < result[3]) {
			cout << 3 << " " << sum3 << endl;
		}

		else {
			cout << 0 << " " << sum3 << endl;
		}
	}

	else if (result[1] < result[2]) {
		if (result[2] > result[3]) {
			cout << 2 << " " << sum2 << endl;
		}

		else if (result[2] < result[3]) {
			cout << 3 << " " << sum3 << endl;
		}

		else {
			cout << 0 << " " << sum3 << endl;
		}
	}

	else {
		if (result[2] > result[3]) {
			cout << 0 << " " << sum2 << endl;
		}

		else if (result[2] < result[3]) {
			cout << 3 << " " << sum3 << endl;
		}

		else {
			cout << 0 << " " << sum3 << endl;
		}
	}

	return 0;
}