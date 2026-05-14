//package com.example.digi_trac_v5.ui.components
//
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.toArgb
//
//@Composable
//fun SignaturePadView(
//    onSigned: (Boolean) -> Unit,
//    onClear: (() -> Unit)? = null,
//    modifier: Modifier = Modifier
//): SignaturePad {
//
//    lateinit var signaturePad: SignaturePad
//
//    AndroidView(
//        modifier = modifier,
//        factory = { context ->
//            SignaturePad(context, null).apply {
//
//                setPenColor(Color.Black.toArgb())
//                setMinWidth(5f)
//                setMaxWidth(10f)
//
//                setOnSignedListener(object : SignaturePad.OnSignedListener {
//                    override fun onStartSigning() {}
//
//                    override fun onSigned() {
//                        onSigned(true)
//                    }
//
//                    override fun onClear() {
//                        onSigned(false)
//                    }
//                })
//
//                signaturePad = this
//            }
//        }
//    )
//
//    return signaturePad
////}