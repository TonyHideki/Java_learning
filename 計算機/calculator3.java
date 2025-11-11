package 計算機;

import java.util.Scanner;

/* コンソール電卓（ans機能付き） */
public class calculator3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double ans = 0; // 前回の結果を保存する変数
        boolean hasAns = false; // ansが有効かどうか

        System.out.println("=== ans機能付き 計算機 ===");
        System.out.println("数値と演算子を入力して計算します。");
        System.out.println("前回の結果を使うには「ans」と入力してください。");
        System.out.println("終了するには 'q' または 'quit' を入力してください。");

        boolean isQuit = false;

        while (true) {
            try {
                System.out.print("\n1つ目の数値を入力してください：");
                String input1 = scanner.next();
                if (input1.equalsIgnoreCase("q") || input1.equalsIgnoreCase("quit")) {
                    isQuit = true;
                    break;
                }

                double num1;
                if (input1.equalsIgnoreCase("ans")) {
                    if (!hasAns) {
                        System.out.println("まだansは存在しません。");
                        continue;
                    }
                    num1 = ans;
                } else {
                    num1 = Double.parseDouble(input1);
                }

                System.out.print("2つ目の数値を入力してください：");
                String input2 = scanner.next();
                if (input2.equalsIgnoreCase("q") || input2.equalsIgnoreCase("quit")) {
                    isQuit = true;
                    break;
                }

                double num2;
                if (input2.equalsIgnoreCase("ans")) {
                    if (!hasAns) {
                        System.out.println("まだansは存在しません。");
                        continue;
                    }
                    num2 = ans;
                } else {
                    num2 = Double.parseDouble(input2);
                }

                System.out.print("演算子を入力してください (+, -, *, /)：");
                String operator = scanner.next();

                double result = 0;

                switch (operator) {
                    case "+":
                        result = num1 + num2;
                        break;
                    case "-":
                        result = num1 - num2;
                        break;
                    case "*":
                        result = num1 * num2;
                        break;
                    case "/":
                        if (num2 == 0) {
                            System.out.println("ゼロで割ることはできません。");
                            continue;
                        }
                        result = num1 / num2;
                        break;
                    default:
                        System.out.println("無効な演算子です。");
                        continue;
                }

                ans = result; // 結果をansに保存
                hasAns = true;

                System.out.println("結果: " + result);
                System.out.println("（ans = " + ans + "）");

            } catch (NumberFormatException e) {
                System.out.println("エラー: 数値を正しく入力してください。");
            } catch (Exception e) {
                System.out.println("予期しないエラーが発生しました。");
            }
        }

        scanner.close();
        if (isQuit) {
            System.out.println("計算機を終了します。");
        }
    }
}