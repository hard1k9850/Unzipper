package com.hardik.unzipper

import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.hardik.unzipper.databinding.FragmentFirstBinding
import net.lingala.zip4j.ZipFile
import net.lingala.zip4j.exception.ZipException
import java.io.File

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            //findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            listFiles()
        }
    }

    private fun listFiles() {
        val zippedFilePath = "/storage/emulated/0/ppt.key.zip"
        val zippedFile = File(zippedFilePath)
        if(zippedFile.exists()){
            Log.d("1###","File exist")
            unzipFile(zippedFile)
        }
    }

    private fun unzipFile(zippedFile: File) {
        val zipFile = ZipFile(zippedFile.absolutePath)
        if (zipFile.isEncrypted) {
            zipFile.setPassword("hello1".toCharArray())
        }
        try {
            zipFile.extractAll("/storage/emulated/0/")
        } catch (e: ZipException) {
            Toast.makeText(activity,"${e.stackTrace}",Toast.LENGTH_LONG).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}