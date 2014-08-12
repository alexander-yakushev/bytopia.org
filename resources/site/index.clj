{:title "Home"
 :skip-title true}

(list
 (for [f (take 10 (reverse (io/list-files :posts)))
       :let [url (post-url f)
             [metadata content] (io/read-doc f)]]
   [:div.entry-preview
    [:h2.entry-title
     [:a {:href url} (:title metadata)]]

    [:div.entry-info
     [:span.entry-date (format-date (:date metadata) "dd MMMM YYYY")]
     [:span.entry-tags "Tags: "
      (->> (:tags metadata)
           (map (fn [tag] [:a {:href (str "/tags/#" tag)} tag]))
           (interpose " "))]
     [:div.clear]]

    [:div.entry-content
     (if-let [synopsis (:synopsis metadata)]
       (list synopsis [:p.readmore
                       [:a {:href url} "Read more ->>"]])
       content)]])

 [:p "For more posts see the " [:a {:href "/archives.html"} "Archives"] " page."])
