package com.lasanimas.simplyvaldo.mylogins.Interfaces;

public interface RecyclerViewToFragmentListener
{
    public void SendSelectLoginInfo(String currentLoginPosition, String type);
    public void setStateDeleteButton(boolean status);
    public void setStateSomeCheckedRadioButton(String color);
    public void allCheckBoxesCheckedManually();
}
