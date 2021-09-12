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

// https://www.acmicpc.net/problem/20001

stack <string> st;

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	string input;
	getline(cin, input);
	getline(cin, input);

	while (input != "������ ����� ��")
	{
		if (input == "����")
		{
			st.push("����");
		}

		else if (input == "������")
		{
			if (st.empty())
			{
				st.push("����");
				st.push("����");
			}

			else
			{
				st.pop();
			}
		}

		getline(cin, input);
	}

	if (st.empty())
	{
		cout << "�������� �����\n";
	}

	else
	{
		cout << "����\n";
	}

	return 0;
}