package com.example.duanlei.testdemo.ContactsDemo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AlphabetIndexer;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.duanlei.testdemo.R;

import java.util.ArrayList;
import java.util.List;

public class Test001Activity extends Activity {

    /**
     * 分组的布局
     */
    private LinearLayout titleLayout;

    /**
     * 分组上显示的字母
     */
    private TextView title;

    /**
     * 联系人ListView
     */
    private ListView contactsListView;

    /**
     * 联系人列表适配器
     */
    private ContactAdapter adapter;

    /**
     * 用于进行字母表分组
     */
    private AlphabetIndexer indexer;

    /**
     * 存储所有手机中的联系人
     */
    private List<Contact> contacts = new ArrayList<Contact>();

    /**
     * 定义字母表的排序规则
     */
    private String alphabet = "#ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     * 上次第一个可见元素，用于滚动时记录标识。
     */
    private int lastFirstVisibleItem = -1;


    /*-------------------------快速滑动---------------------------*/
    /**
     * 字母快速滑动按钮
     */
    //private Button alphabetButton;
    private View alphabetButton;

    private RelativeLayout sectionToastLayout;
    private TextView sectionToastText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test001);

        alphabetButton = findViewById(R.id.alphabetView);

        sectionToastLayout = findViewById(R.id.section_toast_layout);
        sectionToastText = findViewById(R.id.section_toast_text);
        setAlpabetListener();

        adapter = new ContactAdapter(this, R.layout.contact_item, contacts);
        titleLayout = (LinearLayout) findViewById(R.id.title_layout);
        title = (TextView) findViewById(R.id.title);
        contactsListView = (ListView) findViewById(R.id.contacts_list_view);
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;

//        Cursor cursor = getContentResolver().query(uri,
//                new String[] { "display_name", "sort_key" }, null,
//                null, "sort_key");

        Cursor cursor = getContentResolver().query(uri,
                new String[]{"display_name", "phonebook_label"}, null,
                null, "phonebook_label");

        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(0);
                String sortKey = getSortKey(cursor.getString(1));
                Contact contact = new Contact();
                contact.setName(name);
                contact.setSortKey(sortKey);
                contacts.add(contact);
            } while (cursor.moveToNext());
        }
        startManagingCursor(cursor);
        indexer = new AlphabetIndexer(cursor, 1, alphabet);
        adapter.setIndexer(indexer);
        if (contacts.size() > 0) {
            setupContactsListView();
        }
    }

    /**
     * 对字母表按钮的touch事件进行监听
     * 设置字母表上的触摸事件，根据当前触摸的位置结合字母表的高度，计算出当前触摸在哪个字母上。
     * 当手指按在字母表上时，展示弹出式分组。手指离开字母表时，将弹出式分组隐藏。
     */
    @SuppressLint("ClickableViewAccessibility")
    private void setAlpabetListener() {
        alphabetButton.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                float alphabetHeight = alphabetButton.getHeight();
                float y = event.getY();
                int sectionPosition = (int) ((y / alphabetHeight) / (1f / 27f));
                if (sectionPosition < 0) {
                    sectionPosition = 0;
                } else if (sectionPosition > 26) {
                    sectionPosition = 26;
                }
                String sectionLetter = String.valueOf(alphabet.charAt(sectionPosition));
                int position = indexer.getPositionForSection(sectionPosition);
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        //alphabetButton.setBackgroundResource(R.drawable.a_z_click);
                        alphabetButton.setBackgroundColor(R.color.colorAccent);
                        sectionToastLayout.setVisibility(View.VISIBLE);
                        sectionToastText.setText(sectionLetter);
                        contactsListView.setSelection(position);
                        break;
                    case MotionEvent.ACTION_MOVE:
                        sectionToastText.setText(sectionLetter);
                        contactsListView.setSelection(position);
                        break;
                    default:
                        //alphabetButton.setBackgroundResource(R.drawable.a_z);
                        alphabetButton.setBackgroundColor(Color.parseColor("#999999"));
                        sectionToastLayout.setVisibility(View.GONE);
                }
                return true;
            }
        });
    }

    /**
     * 为联系人ListView设置监听事件，根据当前的滑动状态来改变分组的显示位置，从而实现挤压动画的效果。
     */
    private void setupContactsListView() {
        contactsListView.setAdapter(adapter);
        contactsListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
                                 int totalItemCount) {
                //获取第一个可见元素的分组值
                int section = indexer.getSectionForPosition(firstVisibleItem);

                //获取下一个分组中第一个元素的位置
                int nextSecPosition = indexer.getPositionForSection(section + 1);

                //当第一个可见元素滑动出屏幕时调用，没有元素滑出屏幕则不调用，使挤压出去的分组标题布局还原
                if (firstVisibleItem != lastFirstVisibleItem) {
                    ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams)
                            titleLayout.getLayoutParams();
                    params.topMargin = 0;
                    titleLayout.setLayoutParams(params);
                    title.setText(String.valueOf(alphabet.charAt(section)));
                }

                //如果下一个分组的第一个元素 等于 第一个可见元素加1，说明下一个分组的布局要和界面的顶部分组布局相碰了
                if (nextSecPosition == firstVisibleItem + 1) {

                    //获取界面上显示的第一个view
                    View childView = view.getChildAt(0);
                    if (childView != null) {
                        int titleHeight = titleLayout.getHeight();

                        //界面上显示的第一个view的底部距离父窗口的位置
                        int bottom = childView.getBottom();
                        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) titleLayout
                                .getLayoutParams();

                        if (bottom < titleHeight) {
                            float pushedDistance = bottom - titleHeight;
                            params.topMargin = (int) pushedDistance;
                            titleLayout.setLayoutParams(params);
                        } else {
                            if (params.topMargin != 0) {
                                params.topMargin = 0;
                                titleLayout.setLayoutParams(params);
                            }
                        }
                    }
                }
                lastFirstVisibleItem = firstVisibleItem;
            }
        });
    }

    /**
     * 获取sort key的首个字符，如果是英文字母就直接返回，否则返回#。
     *
     * @param sortKeyString 数据库中读取出的sort key
     * @return 英文字母或者#
     */
    private String getSortKey(String sortKeyString) {
        String key = sortKeyString.substring(0, 1).toUpperCase();
        if (key.matches("[A-Z]")) {
            return key;
        }
        return "#";
    }
}
