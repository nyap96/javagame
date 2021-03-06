/*
 * Created on 2005/05/04
 *
 */

/**
 * 文字認識
 * @author mori
 */
public class NNTest {
    public static void main(String[] args) {
        NeuralNetwork nn = new NeuralNetwork();
        nn.init(9, 50, 9);
        nn.setLearningRate(0.2);
        
        // 訓練データの作成
        double[][] trainingSet = new double[][] {
                {1,0,0,0,0,0,0,0},
                {0,1,0,0,0,0,0,0},
                {0,0,1,0,0,0,0,0},
                {0,0,0,1,0,0,0,0},
                {0,0,0,0,1,0,0,0},
                {0,0,0,0,0,1,0,0},
                {0,0,0,0,0,0,1,0},
                {0,0,0,0,0,0,0,1},
        };
        
        // 教師信号
        double[][] teacherSet = new double[][] {
                {0,0,0,0,0,0,0,1},
                {0,0,0,0,0,0,1,0},
                {0,0,0,0,0,1,0,0},
                {0,0,0,0,1,0,0,0},
                {0,0,0,1,0,0,0,0},
                {0,0,1,0,0,0,0,0},
                {0,1,0,0,0,0,0,0},
                {1,0,0,0,0,0,0,0},
        };
            
        // 訓練データを学習
        double error = 1.0;
        int count = 0;
        while ((error > 0.0001) && (count < 50000)) {
            error = 0;
            count++;
            // 各訓練データを誤差が小さくなるまで繰り返し学習
            for (int i=0; i<trainingSet.length; i++) {
                // 入力値を設定
                for (int j=0; j<trainingSet[i].length; j++) {
                    nn.setInput(j, trainingSet[i][j]);
                }
                // 教師信号を設定
                for (int j=0; j<teacherSet[i].length; j++) {
                    nn.setTeacherValue(j, teacherSet[i][j]);
                }
                // 学習開始
                nn.feedForward();
                error += nn.calculateError();
                nn.backPropagate();
            }
            error /= trainingSet.length;
            System.out.println(count + "\t" + error);
        }
        
        // 学習完了
        nn.setInput(0, 1);
        nn.setInput(1, 0);
        nn.setInput(2, 0);
        nn.setInput(3, 0);
        nn.setInput(4, 0);
        nn.setInput(5, 0);
        nn.setInput(6, 0);
        nn.setInput(7, 0);
        nn.feedForward();   // 出力を計算
        int id = nn.getMaxOutputID();
        System.out.println(id + " " + nn.getOutput(id));
    }
}
