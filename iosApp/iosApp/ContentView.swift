import SwiftUI
import shared

func greet() -> String {
    return Greeting().greeting()
}

struct ContentView: View {
    
    @ObservedObject private(set) var characterViewModel: MyCharacterViewModel
        
    var body: some View {
        
        NavigationView {
            listView()
        }

    }
    
    private func listView() -> AnyView {
        switch characterViewModel.characterList {
            case .loading:
                return AnyView(Text("Loading...").multilineTextAlignment(.center))
            case .result(let characterList):
                return AnyView(List(characterList) { character in
//                    CharacterListItemView(characterResponse: character)
                    Text(character.name ?? "error name")
                })
        case .error(let description):
            return AnyView(Text(description).multilineTextAlignment(.center))
        }
    }
}

extension ContentView {
    
    enum LoadableCharacter {
        case loading
        case result([CharacterResponse])
        case error(String)
    }

    class MyCharacterViewModel: ObservableObject {
        let repository: Repository
        
        @Published var characterList = LoadableCharacter.loading

        init(repository: Repository) {
            self.repository = repository
            self.loadCharacterList()
        }

        func loadCharacterList() {
            self.characterList = .loading
            
            repository.testGetCharacterList(completionHandler: { characterList, error in
                if let characterList = characterList {
                    print(characterList)
                    self.characterList = .result(characterList)
                } else {
                    self.characterList = .error(error?.localizedDescription ?? "error")
                }
            })
        }
    }
}

extension CharacterResponse: Identifiable { }

extension ContentView {
    
    enum LoadableCharacter {
        case loading
        case result([CharacterResponse])
        case error(String)
    }

    class MyCharacterViewModel: ObservableObject {
        let repository: Repository
        
        @Published var characterList = LoadableCharacter.loading

        init(repository: Repository) {
            self.repository = repository
            self.loadCharacterList()
        }

        func loadCharacterList() {
            self.characterList = .loading
            
            repository.testGetCharacterList(completionHandler: { characterList, error in
                if let characterList = characterList {
                    print(characterList)
                    self.characterList = .result(characterList)
                } else {
                    self.characterList = .error(error?.localizedDescription ?? "error")
                }
            })
        }
    }
}

extension CharacterResponse: Identifiable { }
