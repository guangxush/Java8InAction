/**
 * @author: guangxush
 * @create: 2019/12/30
 */
public class WordCounter {
    private final int counter;
    private final boolean lastSpace;

    public WordCounter(int counter, boolean lastSpace) {
        this.counter = counter;
        this.lastSpace = lastSpace;
    }

    /**
     * 和迭代算法一样， accumulate 方 法 一 个个遍历Character
     * @param c
     * @return
     */
    public WordCounter accumulate(Character c) {
        if (Character.isWhitespace(c)) {
            return lastSpace ?
                    this :
                    new WordCounter(counter, true);
        } else {
            //上一个字符是空格，而 当前遍历的字符不是 空格时，将单词计数器 加一
            return lastSpace ?
                    new WordCounter(counter + 1, false) :
                    this;
        }
    }

    /**
     * 合并两个Word- Counter，把其 计数器加起来
     * @param wordCounter
     * @return
     */
    public WordCounter combine(WordCounter wordCounter) {
        //仅需要计数器 的总和，无需关 心lastSpace
        return new WordCounter(counter + wordCounter.counter, wordCounter.lastSpace);
    }

    public int getCounter() {
        return counter;
    }
}
