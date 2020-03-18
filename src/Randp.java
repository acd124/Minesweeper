
public class Randp {
    private int[] nums;
    private int numsLeft;
	
    public Randp(int n) {
        this.numsLeft = n;
        this.nums = new int[n];
        this.initNums(n);
    }
	
private void initNums(int n) {
        for(int i = 0; i < n; i++) {
        	nums[i] = i + 1;
        }
    }
	
    public int nextInt() {
        // Math.random() should never be called more than once
        // when this method is called.  Recursion is not allowed.
    	if(numsLeft == 0) {
    		return 0;
    	}
    	int index = (int)Math.floor((numsLeft * Math.random()));
        int value = nums[index];
        nums[index] = nums[numsLeft - 1];
        nums[numsLeft - 1] = 0;
        numsLeft--;
        //return "[" + value + ", " + index + "]";
        return value;
    }
}