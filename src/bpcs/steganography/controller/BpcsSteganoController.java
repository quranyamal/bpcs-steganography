package bpcs.steganography.controller;

import bpcs.steganography.model.BitPlane;
import bpcs.steganography.model.Image;
import bpcs.steganography.model.Message;

public class BpcsSteganoController {
    
    public BpcsSteganoController(){        
    }

    private void fillPlane(BitPlane fillable, boolean[][] fill) {
        for (int i=0; i<8; i++) {
            for (int j=0; j<8; j++) {
                fillable.setMatrix(i, j, fill[i][j]);
            }
        }
    }
    
    public void injectMessage(Image image, Message message) {
        // belum selesai
        
        int msgSeg=0, chunkIdx=0;
        while (msgSeg<message.getNumSegment() && chunkIdx<image.getNumChunk()) {
            
            msgSeg++;
        }
        fillPlane(image.getFillablePlane(), Message.wc);
    }
    
}
