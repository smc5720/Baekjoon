#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>
#include <deque>
#include <queue>
#include <map>
#include <stack>
#include <set>

using namespace std;

// https://www.acmicpc.net/problem/1920

set <int> s;

int N, M;
int ans[100000];

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> N;

	for (int i = 0; i < N; i++)
	{
		int n;
		cin >> n;
		s.insert(n);
	}

	cin >> M;

	for (int i = 0; i < M; i++)
	{
		int n;
		cin >> n;
		ans[i] = s.count(n);
	}

	for (int i = 0; i < M; i++)
	{
		cout << ans[i] << "\n";
	}

	return 0;
}