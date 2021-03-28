package com.sixthank.gogo.src.main.mypage.interfaces;

import com.sixthank.gogo.src.main.mypage.models.MyPageResponse;

public interface MyPageFragmentView {
    void getMemberSuccess(MyPageResponse.Data data);
    void getMemberFailure(String message);

    void patchMemberSuccess(MyPageResponse.Data data);
    void patchMemberFailure(String message);
}
