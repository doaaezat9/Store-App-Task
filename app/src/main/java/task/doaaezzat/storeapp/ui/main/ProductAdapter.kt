package task.doaaezzat.storeapp.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import task.doaaezzat.storeapp.databinding.ProductItemBinding
import task.doaaezzat.storeapp.model.Product

class ProductAdapter (val clickListener: ProductClickListener) : ListAdapter<Product, ProductAdapter.ViewHolder>(
    ProductDiffCallback()
) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = getItem(position)
        holder.bind(product,clickListener)
    }


    class ViewHolder private constructor(val binding: ProductItemBinding): RecyclerView.ViewHolder(binding.root){

        fun bind (product: Product, clickListener: ProductClickListener){
            binding.product = product
            binding.clicklistener = clickListener
            binding.executePendingBindings()
        }
        companion object{
            fun from(parent : ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ProductItemBinding.inflate(layoutInflater,parent,false)
                return ViewHolder(binding)
            }
        }
    }


    class ProductDiffCallback() : DiffUtil.ItemCallback<Product>(){
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return  oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }
    }


    class ProductClickListener(val clickListener: (product : Product)->Unit){
        fun onClick(product: Product) = clickListener(product)
    }

}