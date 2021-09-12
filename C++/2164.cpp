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

// https://www.acmicpc.net/problem/2164

int N;
queue <int> q;

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> N;

	for (int i = 1; i <= N; i++)
	{
		q.push(i);
	}

	while (q.size() > 1)
	{
		q.pop();
		q.push(q.front());
		q.pop();
	}

	cout << q.front() << "\n";

	return 0;
}