package search

import topic.Topic

class SearchController {

    def index() {
        List<Topic> topicList = Topic.findAllByNameIlike("%${params.searchObject}%")
        render(view: 'search', model: [topicList: topicList, searchObject: params.searchObject])
    }
}
