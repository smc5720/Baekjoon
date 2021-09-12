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

// https://www.acmicpc.net/problem/9935

string sentence, bomb;
char ans[1000000];

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> sentence >> bomb;

	int senSize, bombSize;
	senSize = sentence.length();
	bombSize = bomb.length();

	int index;
	index = 0;

	for (int i = 0; i < senSize; i++)
	{
		ans[index] = sentence[i];

		if (index >= bombSize - 1 && sentence[i] == bomb[bombSize - 1])
		{
			int t;
			t = index;

			for (int j = bombSize - 1; j >= 0; j--)
			{
				if (t < 0)
				{
					break;
				}

				if (bomb[j] == ans[t])
				{
					t -= 1;
				}

				else if (bomb[j] != ans[t])
				{
					index += 1;
					break;
				}

				if (j == 0)
				{
					index = t + 1;
				}
			}
		}

		else
		{
			index += 1;
		}
	}

	if (index == 0)
	{
		cout << "FRULA\n";
	}

	else
	{
		for (int i = 0; i < index; i++)
		{
			cout << ans[i];
		}

		cout << "\n";
	}

	return 0;
}