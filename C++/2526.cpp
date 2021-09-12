#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
using namespace std;

// https://www.acmicpc.net/problem/2526

int N, P;
vector<int> numVec;
vector<int>::iterator iter;

bool isFinish;

int main(void)
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> N >> P;

	isFinish = true;

	int k;

	k = N;

	numVec.push_back(k);

	while (isFinish)
	{
		k = k * N % P;

		iter = find(numVec.begin(), numVec.end(), k);

		if (iter != numVec.end())
		{
			isFinish = false;

			int n;

			n = iter - numVec.begin();
			n = numVec.size() - n;

			cout << n << "\n";
		}

		else
		{
			numVec.push_back(k);
		}
	}

	return 0;
}