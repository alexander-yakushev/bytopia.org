{:title "Home"
 :skip-title true}

(list
 (for [f (take 10 (reverse (io/list-files :posts)))
       :let [url (post-url f)
             [metadata content] (io/read-doc f)]]
   [:div.post-outer
    [:div.post.hentry
     [:div.bposttitle
      [:h2.post-title.entry-title
       [:a {:href url} (:title metadata)]]

      [:div.entry-meta
       [:span.entry-date (format-date (:date metadata) "dd MMMM YYYY")]
       [:span.entry-tags "Tags: "
        (->> (:tags metadata)
             (map (fn [tag] [:a {:href (str "/tags/#" tag)} tag]))
             (interpose " "))]
       [:div.clear]]]
     [:div.entry
      [:div.post-body.entry-summary
       (if-let [^String synopsis (:synopsis metadata)]
         (let [lastp (.lastIndexOf synopsis "<p>")]
           (list (if-not (= lastp (.indexOf synopsis "<p>"))
                   (subs synopsis 0 lastp) synopsis)
                 [:p.readmore
                  [:a {:href url} "Read more ->>"]])))]]]])

 [:p "For more posts see the " [:a {:href "/archives.html"} "Archives"] " page."])
