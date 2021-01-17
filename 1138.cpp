#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>

using namespace std;

// https://www.acmicpc.net/problem/1138

int N;
int numArray[11];
vector<int> result;

int main()
{
	cin >> N;

	for (int i = 1; i <= N; i++)
	{
		cin >> numArray[i];
	}

	for (int i = N; i >= 1; i--)
	{
		if (i == N)
		{
			result.push_back(i);
		}

		else
		{
			int s;
			s = numArray[i];
			result.insert(result.begin() + s, i);
		}
	}

	for (int i = 0; i < N; i++)
	{
		cout << result[i] << " ";
	}

	cout << "\n";

	return 0;
}