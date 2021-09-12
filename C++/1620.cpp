#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>
#include <deque>
#include <queue>
#include <map>

using namespace std;

// https://www.acmicpc.net/problem/1620

int N, M;
map <string, string> nameNum;
map <string, string> numName;
string st[100001];

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> N >> M;

	for (int i = 1; i <= N; i++)
	{
		pair <string, string> nameP;
		pair <string, string> numP;

		string s;
		cin >> s;

		nameP.first = s;
		numP.second = s;

		numP.first = to_string(i);
		nameP.second = to_string(i);

		nameNum.insert(nameP);
		numName.insert(numP);
	}

	for (int i = 1; i <= M; i++)
	{
		string s;
		cin >> s;

		st[i] = s;
	}

	for (int i = 1; i <= M; i++)
	{
		if (nameNum.count(st[i]) > 0)
		{
			cout << nameNum[st[i]] << "\n";
		}

		else
		{
			cout << numName[st[i]] << "\n";
		}
	}

	return 0;
}