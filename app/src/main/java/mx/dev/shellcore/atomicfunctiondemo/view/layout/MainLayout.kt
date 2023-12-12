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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import mx.dev.shellcore.atomicfunctiondemo.R
import mx.dev.shellcore.atomicfunctiondemo.ui.theme.AtomicFunctionDemoTheme
import mx.dev.shellcore.atomicfunctiondemo.view.model.InfoVO
import mx.dev.shellcore.atomicfunctiondemo.view.util.LoadingStatus
import mx.dev.shellcore.atomicfunctiondemo.view.vm.MainViewModel
import mx.dev.shellcore.atomicfunctiondemo.view.vm.SecondaryViewModel

@Preview
@Composable
fun MainLayoutPreview() {
    AtomicFunctionDemoTheme {
        MainLayout(
            InfoVO(
                loading = LoadingStatus.LOADING,
                info = null
            ),
            InfoVO(
                loading = LoadingStatus.LOADING,
                info = null
            )
        )
    }
}

@Composable
fun MainLayoutParent() {
    val vm: MainViewModel = viewModel()
    val vm2: SecondaryViewModel = viewModel()
    val infoVO = vm.infoVO.collectAsState().value
    val infoVO2 = vm2.infoVO.collectAsState().value
    MainLayout(
        infoVO = infoVO,
        infoVO2 = infoVO2,
        onClickLoadInfo = {
            vm.loadInfo()
        },
        onClickLoadInfo2 = {
            vm2.loadInfo()
        }
    )
}

@Composable
fun MainLayout(
    infoVO: InfoVO,
    infoVO2: InfoVO,
    onClickLoadInfo: () -> Unit = {},
    onClickLoadInfo2: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.surface)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(4.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = MaterialTheme.colorScheme.surface)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        modifier = Modifier.wrapContentWidth(),
                        color = MaterialTheme.colorScheme.onSurface,
                        text = "Title:"
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        modifier = Modifier.weight(1f),
                        color = MaterialTheme.colorScheme.onSurface,
                        text = infoVO.info?.title ?: "No title"
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        modifier = Modifier.wrapContentWidth(),
                        color = MaterialTheme.colorScheme.onSurface,
                        text = "Description:"
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        modifier = Modifier.weight(1f),
                        color = MaterialTheme.colorScheme.onSurface,
                        text = infoVO.info?.description ?: "No description"
                    )
                }

                if (infoVO.loading == LoadingStatus.LOADING) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(24.dp)
                    )
                }
                if (infoVO.loading == LoadingStatus.ERROR) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            imageVector = ImageVector.vectorResource(R.drawable.ic_error),
                            tint = MaterialTheme.colorScheme.error,
                            contentDescription = "Error"
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(
                            modifier = Modifier.weight(1f),
                            color = MaterialTheme.colorScheme.error,
                            text = "Error loading info",
                        )
                    }
                }


            }
        }

        Button(
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ),
            onClick = {
                onClickLoadInfo()
            }
        ) {
            Text(text = "Load info")
        }

        Card(
            modifier = Modifier
                .fillMaxWidth(),
            elevation = CardDefaults.cardElevation(4.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = MaterialTheme.colorScheme.surfaceVariant)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        modifier = Modifier.wrapContentWidth(),
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        text = "Title:"
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        modifier = Modifier.weight(1f),
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        text = infoVO2.info?.title ?: "No title"
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        modifier = Modifier.wrapContentWidth(),
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        text = "Description:"
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        modifier = Modifier.weight(1f),
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        text = infoVO2.info?.description ?: "No description"
                    )
                }

                if (infoVO2.loading == LoadingStatus.LOADING) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(24.dp),
                        color = MaterialTheme.colorScheme.secondary,
                    )
                }
                if (infoVO2.loading == LoadingStatus.ERROR) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            imageVector = ImageVector.vectorResource(R.drawable.ic_error),
                            tint = MaterialTheme.colorScheme.error,
                            contentDescription = "Error"
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(
                            modifier = Modifier.weight(1f),
                            color = MaterialTheme.colorScheme.error,
                            text = "Error loading info",
                        )
                    }
                }


            }
        }

        Button(
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.onSecondary
            ),
            onClick = {
                onClickLoadInfo2()
            }
        ) {
            Text(text = "Load info")
        }
    }
}
