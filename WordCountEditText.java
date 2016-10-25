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
	 * ����ͳ���ı���ʽ
	 * @param type <br> CountType.SINGULAR ����ʾʣ������ <br>CountType.PERCENTAGE ͬʱ��ʾʣ��������������
	 * @return 
	 */
	public WordCountEditText setCountType(CountType type){
		this.defType = type;
		return this;
	}
	/**
	 * ���÷ָ�����ɫ
	 * @param color
	 * @return
	 */
	public WordCountEditText setLineColor(int color){
		vLine.setBackgroundColor(color);
		return this;
	}
	/**
	 * �����������
	 * @param max
	 * @return
	 */
	public WordCountEditText setMaxWord(int max){
		this.defMax = max;
		return this;
	}
	/**
	 * ������ʾ�ı�
	 * @param hint
	 * @return
	 */
	public WordCountEditText setHint(String hint){
		etContent.setHint(hint);
		return this;
	}
	/**
	 * ������ʾ�ı���ɫ
	 * @param color
	 * @return
	 */
	public WordCountEditText setHintColor(int color){
		etContent.setHintTextColor(color);
		return this;
	}
	/**
	 * ���������ı��Ĵ�С
	 * @param size
	 * @return
	 */
	public WordCountEditText setEditTextSize(int size){
		etContent.setTextSize(size);
		return this;
	}
	/**
	 * ���������ı�����ɫ
	 * @param color
	 * @return
	 */
	public WordCountEditText setEditTextColor(int color){
		etContent.setTextColor(color);
		return this;
	}
	
	//�ı�����
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
    //ͳ�����ݸı�
    private void displayCount() {
        if(defType==CountType.SINGULAR){
            tvNum.setText((defMax - etContent.getText().length())+"");
        }else if(defType==CountType.PERCENTAGE){
            tvNum.setText(defMax-(defMax - etContent.getText().length())+"/"+defMax);
        }

    }
    
   /**
    * ���ô˷�����ʾ�����ؼ�
    */
    public void show(){
    	this.setVisibility(View.VISIBLE);
        if(defType==CountType.SINGULAR){//����1
            tvNum.setText(String.valueOf(defMax));
        }else if(defType==CountType.PERCENTAGE){//����2
            tvNum.setText(0+"/"+defMax);
        }       
        etContent.addTextChangedListener(mTextWatcher);
        
    }
}

