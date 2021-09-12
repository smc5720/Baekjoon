#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>
#include <deque>
#include <queue>
#include <map>
#include <stack>

using namespace std;

// https://www.acmicpc.net/problem/6198

int N;
long long ans;
stack <int> heightStack;

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> N;
	ans = 0;

	for (int i = 0; i < N; i++)
	{
		int h;
		cin >> h;

		while (!heightStack.empty() && heightStack.top() <= h)
		{
			heightStack.pop();
		}

		heightStack.push(h);
		ans += heightStack.size() - 1;
	}

	cout << ans << "\n";

	return 0;
}