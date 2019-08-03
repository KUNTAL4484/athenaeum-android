package tech.aftershock.athenaeum.libs;

import android.view.View;

public interface RecyclerItemClickListener {

    void onClick(View item, int position);
    void onLongClick(View item, int position);
}
