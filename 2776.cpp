#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>
#include <deque>
#include <queue>
#include <map>

using namespace std;

// https://www.acmicpc.net/problem/2776

int T, N, M;

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> T;

	for (int i = 0; i < T; i++)
	{
		map <int, bool> m;
		int arr[1000000];

		cin >> N;

		for (int j = 0; j < N; j++)
		{
			pair <int, bool> p;
			cin >> p.first;
			p.second = true;

			m.insert(p);
		}

		cin >> M;

		for (int j = 0; j < M; j++)
		{
			cin >> arr[j];
		}

		for (int j = 0; j < M; j++)
		{
			if (m.count(arr[j]) > 0)
			{
				cout << 1 << "\n";
			}

			else
			{
				cout << 0 << "\n";
			}
		}
	}

	return 0;
}