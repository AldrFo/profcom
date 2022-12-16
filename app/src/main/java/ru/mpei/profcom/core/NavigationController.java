package ru.mpei.profcom.core;

import android.os.Bundle;

public interface NavigationController {

    void navigate(int fragmentId, Bundle bundle);

    void clear();

}
