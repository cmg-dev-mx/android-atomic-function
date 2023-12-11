package mx.dev.shellcore.atomicfunctiondemo.view.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import mx.dev.shellcore.atomicfunctiondemo.ui.theme.AtomicFunctionDemoTheme
import mx.dev.shellcore.atomicfunctiondemo.view.util.LoadingStatus
import mx.dev.shellcore.atomicfunctiondemo.view.vm.MainViewModel

@Preview
@Composable
fun MainLayoutPreview() {
    AtomicFunctionDemoTheme {
        MainLayout()
    }
}

@Composable
fun MainLayout() {

    val vm: MainViewModel = viewModel()
    val infoVO = vm.infoVO.collectAsState().value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.surface)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(4.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        modifier = Modifier.wrapContentWidth(),
                        text = "Title:"
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        modifier = Modifier.weight(1f),
                        text = infoVO.info?.title ?: "No title"
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        modifier = Modifier.wrapContentWidth(),
                        text = "Description:"
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        modifier = Modifier.weight(1f),
                        text = infoVO.info?.description ?: "No description"
                    )
                }

                if (infoVO.loading == LoadingStatus.LOADING) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(24.dp)
                    )
                }
            }
        }

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                vm.loadInfo()
            }
        ) {
            Text(text = "Load info")
        }
    }
}
