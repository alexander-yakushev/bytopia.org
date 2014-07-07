{:title "Home"
 :skip-title true}

(list
 (for [f (take 10 (reverse (io/list-files :posts)))
       :let [url (post-url f)
             [metadata content] (io/read-doc f)]]
   [:div {:class "entry-preview"}
    [:h2 {:class "entry-title"}
     [:a {:href url} (:title metadata)]]

    [:div {:class "entry-info"}
     [:span {:class "entry-date"} (format-date (:date metadata) "dd MMMM YYYY")]
     (apply conj [:span {:class "entry-tags"} "Tags: "]
            (interpose " "
                       (for [tag (:tags metadata)]
                         [:a {:href (str "/tags/#" tag)} tag])))
     [:div {:class "clear"}]]

    [:div {:class "entry-content"}
     ;; metadata
     (if (:synopsis metadata)
       (list (:synopsis metadata)
             [:p {:class "readmore"}
              [:a {:href url} "Read more ->>"]])
       content)]])

 [:p "For more posts see the " [:a {:href "/archives.html"} "Archives"] " page."])
