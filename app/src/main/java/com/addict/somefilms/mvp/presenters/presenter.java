package com.addict.somefilms.mvp.presenters;

/**
 * Interface that represents a Presenter in the model view presenter Pattern
 * defines methods to manage the Activity / Fragment lifecycle
 */
public abstract class presenter {
    /**
     * Called when the presenter is initialized
     */
    public abstract void start();

    /**
     * Called when the presenter is stop, i.e when an activity
     * or a fragment finishes
     */
    public abstract void stop();
}
