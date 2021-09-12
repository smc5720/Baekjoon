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

// https://www.acmicpc.net/problem/17219

map <string, string> cache;
int N, M;

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> N >> M;

	for (int i = 0; i < N; i++)
	{
		pair <string, string> p;
		cin >> p.first >> p.second;
		cache.insert(p);
	}

	for (int i = 0; i < M; i++)
	{
		string s;
		cin >> s;
		cout << cache[s] << "\n";
	}

	return 0;
}