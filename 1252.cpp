#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <cstring>

using namespace std;

// https://www.acmicpc.net/problem/1252

string A, B;
int bigSize, smallSize;
bool aIsBig, isSame;
vector<int> result;

int main(void)
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> A >> B;

	if (A.size() > B.size())
	{
		aIsBig = true;
		isSame = false;

		bigSize = A.size();
		smallSize = B.size();
	}

	else if (A.size() < B.size())
	{
		aIsBig = false;
		isSame = false;

		smallSize = A.size();
		bigSize = B.size();
	}

	else
	{
		aIsBig = false;
		isSame = true;

		smallSize = A.size();
		bigSize = B.size();
	}

	bool isTwo;
	isTwo = false;

	if (isSame)
	{
		for (int i = bigSize - 1; i >= 0; i--)
		{
			int num1, num2;

			num1 = A[i] - 48;
			num2 = B[i] - 48;

			int sum;
			sum = num1 + num2;

			if (isTwo)
			{
				sum += 1;
				isTwo = false;
			}

			if (sum >= 2)
			{
				sum -= 2;
				isTwo = true;
			}

			result.push_back(sum);

			if (i == 0 && isTwo == true)
			{
				result.push_back(1);
			}
		}
	}

	else
	{
		if (aIsBig)
		{
			int k;
			k = bigSize - smallSize;

			for (int i = smallSize - 1; i >= 0; i--)
			{
				int num1, num2;

				num1 = A[i + k] - 48;
				num2 = B[i] - 48;

				int sum;
				sum = num1 + num2;

				if (isTwo)
				{
					sum += 1;
					isTwo = false;
				}

				if (sum >= 2)
				{
					sum -= 2;
					isTwo = true;
				}

				result.push_back(sum);
			}

			for (int i = k - 1; i >= 0; i--)
			{
				int num;
				num = A[i] - 48;

				if (isTwo)
				{
					num += 1;
					isTwo = false;
				}

				if (num >= 2)
				{
					num -= 2;
					isTwo = true;
				}

				result.push_back(num);

				if (i == 0 && isTwo == true)
				{
					result.push_back(1);
				}
			}
		}

		else
		{
			int k;
			k = bigSize - smallSize;

			for (int i = smallSize - 1; i >= 0; i--)
			{
				int num1, num2;

				num1 = B[i + k] - 48;
				num2 = A[i] - 48;

				int sum;
				sum = num1 + num2;

				if (isTwo)
				{
					sum += 1;
					isTwo = false;
				}

				if (sum >= 2)
				{
					sum -= 2;
					isTwo = true;
				}

				result.push_back(sum);
			}

			for (int i = k - 1; i >= 0; i--)
			{
				int num;
				num = B[i] - 48;

				if (isTwo)
				{
					num += 1;
					isTwo = false;
				}

				if (num >= 2)
				{
					num -= 2;
					isTwo = true;
				}

				result.push_back(num);

				if (i == 0 && isTwo == true)
				{
					result.push_back(1);
				}
			}
		}
	}

	bool isOne;
	isOne = false;

	for (int i = result.size() - 1; i >= 0; i--)
	{
		if (isOne)
		{
			cout << result[i];
		}

		else
		{
			if (result[i] == 1)
			{
				isOne = true;
				cout << result[i];
			}

			else
			{
				if (i == 0)
				{
					cout << 0;
				}
			}
		}
	}

	cout << "\n";

	return 0;
}