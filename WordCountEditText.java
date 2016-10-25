import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;



public class WordCountEditText extends LinearLayout {
	private EditText etContent;
	private TextView tvNum;
	private View vLine;
	public enum CountType {SINGULAR,PERCENTAGE};
	private int defMax = 100;
	private CountType defType = CountType.SINGULAR;

	public WordCountEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater.from(context).inflate(R.layout.word_count_edittext, this, true);
        etContent = (EditText) findViewById(R.id.et);
        tvNum = (TextView) findViewById(R.id.tvNum);
        vLine = findViewById(R.id.vLine);      
        this.setVisibility(View.GONE);
	}
	
	public WordCountEditText(Context context) {
		this(context,null);
		// TODO Auto-generated constructor stub
	}
	/**
	 * 设置统计文本样式
	 * @param type <br> CountType.SINGULAR 仅显示剩余字数 <br>CountType.PERCENTAGE 同时显示剩余字数与总字数
	 * @return 
	 */
	public WordCountEditText setCountType(CountType type){
		this.defType = type;
		return this;
	}
	/**
	 * 设置分割线颜色
	 * @param color
	 * @return
	 */
	public WordCountEditText setLineColor(int color){
		vLine.setBackgroundColor(color);
		return this;
	}
	/**
	 * 设置最大字数
	 * @param max
	 * @return
	 */
	public WordCountEditText setMaxWord(int max){
		this.defMax = max;
		return this;
	}
	/**
	 * 设置提示文本
	 * @param hint
	 * @return
	 */
	public WordCountEditText setHint(String hint){
		etContent.setHint(hint);
		return this;
	}
	/**
	 * 设置提示文本颜色
	 * @param color
	 * @return
	 */
	public WordCountEditText setHintColor(int color){
		etContent.setHintTextColor(color);
		return this;
	}
	/**
	 * 设置输入文本的大小
	 * @param size
	 * @return
	 */
	public WordCountEditText setEditTextSize(int size){
		etContent.setTextSize(size);
		return this;
	}
	/**
	 * 设置输入文本的颜色
	 * @param color
	 * @return
	 */
	public WordCountEditText setEditTextColor(int color){
		etContent.setTextColor(color);
		return this;
	}
	
	//文本监听
	private TextWatcher mTextWatcher = new TextWatcher() {
        public void afterTextChanged(Editable s) {      	                              
            if(s.length()>defMax){
            	s.delete(0, 1);
            }
           // System.out.println(s.length());
            displayCount();
        }

        public void beforeTextChanged(CharSequence s, int start, int count,int after) {}

        public void onTextChanged(CharSequence s, int start, int before,int count) {}
    };
    //统计内容改变
    private void displayCount() {
        if(defType==CountType.SINGULAR){
            tvNum.setText((defMax - etContent.getText().length())+"");
        }else if(defType==CountType.PERCENTAGE){
            tvNum.setText(defMax-(defMax - etContent.getText().length())+"/"+defMax);
        }

    }
    
   /**
    * 调用此方法显示整个控件
    */
    public void show(){
    	this.setVisibility(View.VISIBLE);
        if(defType==CountType.SINGULAR){//类型1
            tvNum.setText(String.valueOf(defMax));
        }else if(defType==CountType.PERCENTAGE){//类型2
            tvNum.setText(0+"/"+defMax);
        }       
        etContent.addTextChangedListener(mTextWatcher);
        
    }
}

