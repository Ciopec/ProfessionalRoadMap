package com.ciopec.interactivestory.model;

import com.ciopec.interactivestory.R;

/**
 * Created by a626983 on 16.07.2017.
 */

public class Story {
    private Page[] pages;

    public Story() {
        pages = new Page[10];

        pages[0] = new Page(R.drawable.page00,
                            R.string.page0,
                            new Choice(R.string.page0_choice1,0),
                            new Choice(R.string.page0_choice2,1));

        pages[1] = new Page(R.drawable.page01,
                R.string.page1,
                new Choice(R.string.page1_choice1, 1),
                new Choice(R.string.page1_choice2, 2));

        pages[2] = new Page(R.drawable.page02,
                R.string.page2,
                new Choice(R.string.page2_choice1, 3),
                new Choice(R.string.page2_choice2, 4));

        pages[3] = new Page(R.drawable.page03,
                R.string.page3,
                new Choice(R.string.page3_choice1, 5),
                new Choice(R.string.page3_choice2, 6));

        pages[4] = new Page(R.drawable.page04,
                R.string.page4,
                new Choice(R.string.page4_choice1, 8),
                new Choice(R.string.page4_choice2, 7));

        pages[5] = new Page(R.drawable.page05,
                R.string.page5,
                new Choice(R.string.page5_choice1, 9),
                new Choice(R.string.page5_choice2, 9));

        pages[6] = new Page(R.drawable.page06,
                R.string.page6,
                new Choice(R.string.page6_choice1, 9),
                new Choice(R.string.page6_choice2, 9));

        pages[7] = new Page(R.drawable.page07,
                R.string.page7,
                new Choice(R.string.page7_choice1, 9),
                new Choice(R.string.page7_choice2, 9));

        pages[8] = new Page(R.drawable.page08,
                R.string.page8,
                new Choice(R.string.page8_choice1, 9),
                new Choice(R.string.page8_choice2, 9));

        pages[9] = new Page(R.drawable.page09, R.string.page9);



    }

    public Page getPage(int pageNumber) {
        if (pageNumber >= pages.length) {
            pageNumber = 0;
        }
        return pages[pageNumber];
    }
}
