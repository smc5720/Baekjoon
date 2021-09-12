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

// https://www.acmicpc.net/problem/5525

int N, M;
string S;

int main(void)
{
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> N >> M >> S;

	char c;
	c = S[0];

	int sum, ans;
	sum = 0;
	ans = 0;

	for (int i = 1; i < M - 1; i++)
	{
		if (c == 'I')
		{
			if (S[i] == 'O' && S[i + 1] == 'I')
			{
				sum += 1;
				i += 1;
			}

			else
			{
				if (sum >= N)
				{
					ans += sum - (N - 1);
				}

				c = S[i];
				sum = 0;
			}	
		}

		else
		{
			if (sum >= N)
			{
				ans += sum - (N - 1);
			}

			c = S[i];
			sum = 0;
		}

		if (i >= M - 2)
		{
			if (sum >= N)
			{
				ans += sum - (N - 1);
			}
		}

		//cout << i << ": " << sum << "\n";
	}

	cout << ans << "\n";

	return 0;
}