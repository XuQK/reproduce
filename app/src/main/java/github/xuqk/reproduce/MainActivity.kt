package github.xuqk.reproduce

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ListItem
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.pullRefreshIndicatorTransform
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType.Companion.Text
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val colorAdapter = ColorAdapter()

    private val list = listOf(
        "one",
        "two",
        "three",
        "four",
        "five",
        "six",
        "seven",
        "eight",
        "nine",
        "ten",
        "eleven",
        "twelve",
        "thirteen",
        "fourteen",
        "fifteen",
        "sixteen",
        "seventeen",
        "eighteen",
        "nineteen",
        "twenty",
        "twenty-one",
        "twenty-two",
        "twenty-three",
        "twenty-four",
        "twenty-five",
        "twenty-six",
        "twenty-seven",
        "twenty-eight",
        "twenty-nine",
        "thirty",
        "thirty-one",
        "thirty-two",
        "thirty-three",
        "thirty-four",
        "thirty-five",
        "thirty-six",
        "thirty-seven",
        "thirty-eight",
        "thirty-nine",
        "forty",
        "forty-one",
        "forty-two",
        "forty-three",
        "forty-four",
        "forty-five",
        "forty-six",
        "forty-seven",
        "forty-eight",
        "forty-nine",
        "fifty",
        "fifty-one",
        "fifty-two",
        "fifty-three",
        "fifty-four",
        "fifty-five",
        "fifty-six",
        "fifty-seven",
        "fifty-eight",
        "fifty-nine",
        "sixty",
        "sixty-one",
        "sixty-two",
        "sixty-three",
        "sixty-four",
        "sixty-five",
        "sixty-six",
        "sixty-seven",
        "sixty-eight",
        "sixty-nine",
        "seventy",
        "seventy-one",
        "seventy-two",
        "seventy-three",
        "seventy-four",
        "seventy-five",
        "seventy-six",
        "seventy-seven",
        "seventy-eight",
        "seventy-nine",
        "eighty",
        "eighty-one",
        "eighty-two",
        "eighty-three",
        "eighty-four",
        "eighty-five",
        "eighty-six",
        "eighty-seven",
        "eighty-eight",
        "eighty-nine",
        "ninety",
        "ninety-one",
        "ninety-two",
        "ninety-three",
        "ninety-four",
        "ninety-five",
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<RecyclerView>(R.id.rv).run {
            layoutManager = LinearLayoutManager(context)
            adapter = colorAdapter
        }
        findViewById<View>(R.id.btn).setOnClickListener {
            if (colorAdapter.data.isEmpty()) {
                colorAdapter.data = list
            } else {
                colorAdapter.data = emptyList()
            }
            colorAdapter.notifyDataSetChanged()
        }
    }
}

private class ColorAdapter : RecyclerView.Adapter<ColorViewHolder>() {
    var data = listOf<String>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder {
        return ColorViewHolder(ComposeView(parent.context))
    }

    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {
        Log.d("MainActivity", "onBindViewHolder: $position")
        (holder.itemView as ComposeView).setContent {
            Log.d("MainActivity", "onBindViewHolder setContent: $position")
            FileListItem(name = data[position])
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}

private class ColorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

@Composable
private fun FileListItem(name: String) {
    Row(
        modifier = Modifier
            .height(60.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .padding(start = 20.dp)
                .background(
                    color = Color.LightGray,
                    shape = RoundedCornerShape(8.dp)
                )
                .size(36.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(painter = painterResource(id = R.drawable.ic_launcher_foreground), contentDescription = null, modifier = Modifier.size(24.dp))
        }
        Column(
            modifier = Modifier
                .padding(start = 12.dp, top = 1.dp, end = 12.dp)
                .weight(1f)
        ) {
            BasicText(
                modifier = Modifier.padding(top = 1.dp),
                text = name,
                style = TextStyle(),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            BasicText(
                modifier = Modifier.padding(top = 1.dp),
                text = "date and size",
                style = TextStyle(),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}
