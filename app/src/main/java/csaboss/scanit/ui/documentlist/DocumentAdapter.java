package csaboss.scanit.ui.documentlist;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import csaboss.scanit.R;
import csaboss.scanit.model.Document;
import csaboss.scanit.ui.documentdetails.DocumentDetailsActivity;

/**
 * Created by Csabi on 2017. 05. 16..
 */

class DocumentAdapter extends RecyclerView.Adapter {
    private List<Document> documents;

    public static class DocumentViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView title;
        TextView body;
        Document actualDocument;

        DocumentViewHolder(final View item) {
            super(item);
            cardView = (CardView) item.findViewById(R.id.card_view);
            title = (TextView) item.findViewById(R.id.document_title);
            body = (TextView) item.findViewById(R.id.document_body);
            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), DocumentDetailsActivity.class);
                    intent.putExtra("DocumentId",actualDocument.getId());
                    v.getContext().startActivity(intent);
                }
            });
        }
    }

    public DocumentAdapter(List<Document> documents) {
        this.documents = documents;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.document_list_item, parent, false);
        DocumentViewHolder vh = new DocumentViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        DocumentViewHolder h = (DocumentViewHolder) holder;
        h.title.setText(documents.get(position).getTitle());
        h.body.setText(documents.get(position).getText());
        h.actualDocument=documents.get(position);
    }

    @Override
    public int getItemCount() {
        return documents == null ? 0 : documents.size();
    }

    public void updateDocumentList(List<Document> documents){
        this.documents=documents;
        notifyDataSetChanged();
    }

}
