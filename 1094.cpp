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

// https://www.acmicpc.net/problem/1094

int X;
int num;

int main()
{ 
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> X;
	num = 64;
	
	int ans;
	ans = 0;

	while (num>0)
	{
		if (X >= num)
		{
			X -= num;
			ans += 1;
		}

		num /= 2;
	}

	cout << ans << "\n";

	return 0;
}