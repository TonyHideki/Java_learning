package calc;

import java.util.Scanner;

/*コンソール電卓のその2(ループ有り)*/
public class Calculator2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== 簡単な計算機 ===");
        System.out.println("数値と演算子を入力して計算します。");
        System.out.println("終了するには 'q' を入力してください。");

        boolean isQuit = false;// 終了判定フラグ,計算機を終了するかどうかを判断するためのフラグ。

        while (true) {
            try {
                System.out.print("1つ目の数値を入力してください：");
                // 「q」または「quit」が入力されたら計算機を終了する。
                String input1 = scanner.next();
                if (input1.equalsIgnoreCase("q") ||
                        input1.equalsIgnoreCase("quit")) {
                    isQuit = true;// 終了判定フラグをtrueにする。
                    break;
                }

                double num1 = Double.parseDouble(input1);

                System.out.print("2つ目の数値を入力してください：");

                String input2 = scanner.next();
                if (input2.equalsIgnoreCase("q") ||
                        input2.equalsIgnoreCase("quit")) {
                    isQuit = true;
                    break;
                }
                double num2 = Double.parseDouble(input2);

                System.out.print("演算子を入力してください：");
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
                System.out.println("結果: " + result);

            } catch (NumberFormatException e) {
                System.out.println("数値を入力してください。");
            } catch (Exception e) {
                System.out.println("予期しないエラーが発生しました。");
            }
        }
        scanner.close();

        if (isQuit) {
            System.out.println("計算機を終了します。");

        } else {
            System.out.println("予期せず計算機を終了しました。");
        }
    }
}
