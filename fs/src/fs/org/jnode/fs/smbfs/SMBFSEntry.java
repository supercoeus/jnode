package org.jnode.fs.smbfs;

import org.jnode.fs.FSEntry;
import org.jnode.fs.FSAccessRights;

import java.io.IOException;

import jcifs.smb.SmbFile;
import jcifs.smb.SmbException;

/**
 * @author Levente S\u00e1ntha
 */
public abstract class SMBFSEntry implements FSEntry {
    SmbFile smbFile;
    SMBFSDirectory parent;

    protected SMBFSEntry(SMBFSDirectory parent, SmbFile smbFile) {
        this.parent = parent;
        this.smbFile = smbFile;
    }

    /**
     * Gets the accessrights for this entry.
     *
     * @throws java.io.IOException
     */
    public FSAccessRights getAccessRights() throws IOException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Gets the directory this entry refers to. This method can only be called
     * if <code>isDirectory</code> returns true.
     *
     * @return The directory described by this entry
     */
    public SMBFSDirectory getDirectory() throws IOException {
        return (SMBFSDirectory) this;
    }

    /**
     * Gets the file this entry refers to. This method can only be called
     * if <code>isFile</code> returns true.
     *
     * @return The file described by this entry
     */
    public SMBFSFile getFile() throws IOException {
        return (SMBFSFile) this;
    }

    /**
     * Gets the last modification time of this entry.
     *
     * @throws java.io.IOException
     */

    public long getLastModified() throws IOException {
        return smbFile.getLastModified();
    }

    /**
     * Gets the name of this entry.
     */
    public String getName() {
        return smbFile.getName();
    }

    /**
     * Gets the directory this entry is a part of.
     */
    public SMBFSDirectory getParent() {
        return null;
    }

    /**
     * Is this entry refering to a (sub-)directory?
     */
    public boolean isDirectory() {
        try {
            return smbFile.isDirectory();
        } catch(SmbException e){
            throw new RuntimeException(e);
        }
    }

    /**
     * Indicate if the entry has been modified in memory (ie need to be saved)
     *
     * @return true if the entry need to be saved
     * @throws java.io.IOException
     */
    public boolean isDirty() throws IOException {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Is this entry refering to a file?
     */
    public boolean isFile() {
        try {
            return smbFile.isFile();
        } catch(SmbException e){
            throw new RuntimeException(e);
        }
    }

    /**
     * Gets the last modification time of this entry.
     *
     * @throws java.io.IOException
     */
    public void setLastModified(long lastModified) throws IOException {

    }

    /**
     * Sets the name of this entry.
     */
    public void setName(String newName) throws IOException {

    }

    /**
     * Gets the filesystem to which this object belongs.
     */
    public SMBFileSystem getFileSystem() {
        return null;
    }

    /**
     * Is this object still valid.
     * <p/>
     * An object is not valid anymore if it has been removed from the filesystem.
     * All invocations on methods (exception this method) of invalid objects
     * must throw an IOException.
     */
    public boolean isValid() {
        return true;
    }
}
