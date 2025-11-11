package 計算機;
import java.util.Scanner;

public class 計算機 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== 簡単な計算機 ===");
        System.out.println("使用可能な演算子: +, -, *, /");
        System.out.println("終了するには 'q' を入力してください\n");
        
        while (true) {
            System.out.print("計算式を入力してください (例: 10 + 5): ");
            String input = scanner.nextLine().trim();
            
            // 終了コマンド
            if (input.equalsIgnoreCase("q") || input.equalsIgnoreCase("quit")) {
                System.out.println("計算機を終了します。");
                break;
            }
            
            // 計算式を解析
            try {
                double result = calculate(input);
                System.out.println("結果: " + result + "\n");
            } catch (Exception e) {
                System.out.println("エラー: " + e.getMessage() + "\n");
            }
        }
        
        scanner.close();
    }
    
    private static double calculate(String expression) {
        // 空白を削除
        expression = expression.replaceAll("\\s+", "");
        
        // 演算子を探す
        char operator = '\0';
        int operatorIndex = -1;
        
        char[] operators = {'+', '-', '*', '/'};
        for (int i = expression.length() - 1; i >= 0; i--) {
            char c = expression.charAt(i);
            for (char op : operators) {
                if (c == op && i > 0) {
                    operator = op;
                    operatorIndex = i;
                    break;
                }
            }
            if (operator != '\0') break;
        }
        
        if (operator == '\0' || operatorIndex == -1) {
            throw new IllegalArgumentException("無効な計算式です。演算子(+, -, *, /)を含めてください。");
        }
        
        // 数値を抽出
        String leftStr = expression.substring(0, operatorIndex);
        String rightStr = expression.substring(operatorIndex + 1);
        
        if (leftStr.isEmpty() || rightStr.isEmpty()) {
            throw new IllegalArgumentException("数値が不足しています。");
        }
        
        double left = Double.parseDouble(leftStr);
        double right = Double.parseDouble(rightStr);
        
        // 計算を実行
        switch (operator) {
            case '+':
                return left + right;
            case '-':
                return left - right;
            case '*':
                return left * right;
            case '/':
                if (right == 0) {
                    throw new ArithmeticException("ゼロで割ることはできません。");
                }
                return left / right;
            default:
                throw new IllegalArgumentException("サポートされていない演算子です。");
        }
    }
}

